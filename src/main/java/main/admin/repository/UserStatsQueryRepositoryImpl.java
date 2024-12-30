package main.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import main.admin.ui.dto.GetDailyRegisterUserResponseDto;
import main.admin.ui.query.UserStatsQueryRepository;
import main.common.utils.TimeCalculator;
import main.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserStatsQueryRepositoryImpl implements UserStatsQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserEntity userEntity = QUserEntity.userEntity;

    @Override
    public List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays) {
        return queryFactory
            .select(
                Projections.fields(
                    GetDailyRegisterUserResponseDto.class,
                    userEntity.create_date.as("date"),
                    userEntity.count().as("count")
                )
            )
            .from(userEntity)
            .where(userEntity.create_date.after(TimeCalculator.getDateDaysAgo(beforeDays)))
            .groupBy(userEntity.create_date)
            .orderBy(userEntity.create_date.asc())
            .fetch();
    }
}
