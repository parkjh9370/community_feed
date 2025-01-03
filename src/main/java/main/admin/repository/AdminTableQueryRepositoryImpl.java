package main.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import main.admin.ui.dto.GetTableListResponse;
import main.admin.ui.dto.post.GetPostTableRequestDto;
import main.admin.ui.dto.post.GetPostTableResponseDto;
import main.admin.ui.dto.users.GetUserTableRequestDto;
import main.admin.ui.dto.users.GetUserTableResponseDto;
import main.admin.ui.query.AdminTableQueryRepository;
import main.auth.repository.entity.QUserAuthEntity;
import main.post.repository.entity.post.QPostEntity;
import main.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminTableQueryRepositoryImpl implements AdminTableQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private static final QUserAuthEntity userAuthEntity = QUserAuthEntity.userAuthEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QPostEntity postEntity = QPostEntity.postEntity;

    @Override
    public GetTableListResponse<GetUserTableResponseDto> getUserTable(
        GetUserTableRequestDto dto
    ) {
        int total = jpaQueryFactory.select(userEntity.id)
            .from(userEntity)
            .where(likeName(dto.getName()))
            .fetch()
            .size();

        // (커버링)인덱스 기준 데이터 가져오기
        List<Long> ids = jpaQueryFactory.select(userEntity.id)
            .from(userEntity)
            .where(likeName(dto.getName()))
            .offset(dto.getOffset())
            .limit(dto.getLimit())
            .fetch();
        List<GetUserTableResponseDto> result = jpaQueryFactory
            .select(
                Projections.fields(
                    GetUserTableResponseDto.class,
                    userEntity.id.as("id"),
                    userAuthEntity.email.as("email"),
                    userEntity.name.as("name"),
                    userAuthEntity.role.as("role"),
                    userEntity.create_dt.as("createdAt"),
                    userEntity.update_dt.as("updatedAt"),
                    userAuthEntity.lastLoginAt.as("lastLoginAt")
                )
            )
            .from(userEntity)
            .join(userAuthEntity).on(userAuthEntity.userId.eq(userEntity.id))
            .where(userEntity.id.in(ids))
            .orderBy(userEntity.id.desc())
            .fetch();

        // 인덱스 적용이 안되어 수십만 데이터 있는 경우 부하 올라감(+느림)
        /*List<GetUserTableResponseDto> result = jpaQueryFactory
            .select(
                Projections.fields(
                    GetUserTableResponseDto.class,
                    userEntity.id.as("id"),
                    userAuthEntity.email.as("email"),
                    userEntity.name.as("name"),
                    userAuthEntity.role.as("role"),
                    userEntity.create_dt.as("createdAt"),
                    userEntity.update_dt.as("updatedAt"),
                    userAuthEntity.lastLoginAt.as("lastLoginAt")
                )
            )
            .from(userEntity)
            .join(userAuthEntity).on(userAuthEntity.userId.eq(userEntity.id))
            .where(likeName(dto.getName()))
            .orderBy(userEntity.id.desc())
            .offset(dto.getOffset())
            .limit(dto.getLimit())
            .fetch();*/

        return new GetTableListResponse<>(total, result);
    }

    @Override
    public GetTableListResponse<GetPostTableResponseDto> getPostTable(GetPostTableRequestDto dto) {
        int total = jpaQueryFactory.select(postEntity.id)
            .from(postEntity)
            .where(
                eqPostId(dto.getPostId())
            )
            .fetch()
            .size();

        List<Long> ids = jpaQueryFactory
            .select(postEntity.id)
            .from(postEntity)
            .where(
                eqPostId(dto.getPostId())
            )
            .orderBy(postEntity.id.desc())
            .offset(dto.getOffset())
            .limit(dto.getLimit())
            .fetch();

        List<GetPostTableResponseDto> result = jpaQueryFactory
            .select(
                Projections.fields(
                    GetPostTableResponseDto.class,
                    postEntity.id.as("postId"),
                    userEntity.id.as("userId"),
                    userEntity.name.as("userName"),
                    postEntity.content.as("content"),
                    postEntity.create_dt.as("createdAt"),
                    postEntity.update_dt.as("updatedAt")
                )
            )
            .from(postEntity)
            .join(userEntity).on(postEntity.user.id.eq(userEntity.id))
            .where(
                postEntity.id.in(ids)
            )
            .orderBy(postEntity.id.desc())
            .fetch();

        return new GetTableListResponse<>(total, result);
    }

    private BooleanExpression likeName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }

        return userEntity.name.like(name + "%");
    }

    private BooleanExpression eqPostId(Long id) {
        if (id == null) {
            return null;
        }

        return postEntity.id.eq(id);
    }
}
