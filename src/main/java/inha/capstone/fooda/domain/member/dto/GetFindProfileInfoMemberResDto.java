package inha.capstone.fooda.domain.member.dto;

import inha.capstone.fooda.domain.feed_image.dto.FeedImageDto;
import inha.capstone.fooda.utils.FeedImageResDto;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetFindProfileInfoMemberResDto {
    String name;

    Long feedCount;

    Long followerCount;

    Long followingCount;

    List<FeedImageResDto> feedImageList;

    public static GetFindProfileInfoMemberResDto from(
            String name,
            Long feedCount,
            Long followerCount,
            Long followingCount,
            List<FeedImageDto> feedImageList) {
        return new GetFindProfileInfoMemberResDto(
                name,
                feedCount,
                followerCount,
                followingCount,
                feedImageList
                        .stream()
                        .map(FeedImageResDto::from)
                        .toList());
    }
}
