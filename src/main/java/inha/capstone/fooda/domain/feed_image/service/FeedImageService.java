package inha.capstone.fooda.domain.feed_image.service;

import inha.capstone.fooda.domain.common.service.S3Service;
import inha.capstone.fooda.domain.feed.entity.Feed;
import inha.capstone.fooda.domain.feed.entity.Menu;
import inha.capstone.fooda.domain.feed.repository.FeedRepository;
import inha.capstone.fooda.domain.feed_image.entity.FeedImage;
import inha.capstone.fooda.domain.feed_image.repository.FeedImageRepository;
import inha.capstone.fooda.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedImageService {
    private final FeedImageRepository feedImageRepository;
    private final S3Service s3Service;

    public void uploadFeedImage(MultipartFile img, Feed feed) throws IOException {
        String fileName = UUID.randomUUID().toString();
        String url = s3Service.upload(img, fileName);
        FeedImage feedImage = FeedImage.builder()
                .feed(feed)
                .fileNameStored(fileName)
                .fileName(img.getOriginalFilename())
                .url(url)
                .build();
        feedImageRepository.save(feedImage);
    }
}