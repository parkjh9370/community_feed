package main.user.repository.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import main.user.application.dto.GetUserListResponseDto;
import main.user.repository.entity.QUserEntity;
import main.user.repository.entity.QUserRelationEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private static final QUserEntity user = QUserEntity.userEntity;
    private static final QUserRelationEntity relation = QUserRelationEntity.userRelationEntity;

    public List<GetUserListResponseDto> getFollowerList(Long userId, Long lastFollowerId) {
        return jpaQueryFactory
            .select(
                Projections.fields(
                    GetUserListResponseDto.class
                )
            )
            .from(relation)
            .join(user).on(relation.followingUserId.eq(user.id))
            .where(
                relation.followerUserId.eq(userId),
                hasLastData(lastFollowerId)
            )
            .orderBy(user.id.desc())
            .limit(20)
            .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }

        return user.id.lt(lastId);
    }
}
