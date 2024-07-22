package com.ict.pretzel.lee.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ict.pretzel.lee.service.MyPageService;
import com.ict.pretzel.common.MyPagePaging;
import com.ict.pretzel.jwt.JwtDecode;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.NoticeVO;
import com.ict.pretzel.vo.ProfileVO;
import com.ict.pretzel.vo.QuestionVO;
import com.ict.pretzel.vo.ReviewVO;
import com.ict.pretzel.vo.WatchListVO;

@RestController
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    private MyPageService myPageService;

    @Autowired
    private MyPagePaging paging;

    @GetMapping("/userdetail")
    public ResponseEntity<?> userdetail(@RequestHeader("Authorization") String token) {
        JwtDecode jwtDecode = new JwtDecode(token);
        String userId = jwtDecode.getUser_id();
        return ResponseEntity.ok(myPageService.userdetail(userId));
    }

    @PostMapping("/watchlist")
    public ResponseEntity<List<WatchListVO>> watchlist(@RequestBody ProfileVO profile) {
        int profileIdx = Integer.parseInt(profile.getProfile_idx());
        return ResponseEntity.ok(myPageService.watchlist(profileIdx));
    }

    @PostMapping("/wishlist")
    public ResponseEntity<List<MovieVO>> wishlist(@RequestBody ProfileVO profile) {
        int profileIdx = Integer.parseInt(profile.getProfile_idx());
        return ResponseEntity.ok(myPageService.wishlist(profileIdx));
    }

    @PostMapping("/questionlist")
    public ResponseEntity<List<QuestionVO>> questionlist(@RequestBody ProfileVO profile) {
        int profileIdx = Integer.parseInt(profile.getProfile_idx());
        return ResponseEntity.ok(myPageService.questionlist(profileIdx));
    }

    @GetMapping("/reviewlist")
    public ResponseEntity<?> reviewlist(@RequestParam(value = "cPage", defaultValue = "1") String cPage, @RequestParam("profile_idx") String profile_idx) {
        try {
            int profileIdx = Integer.parseInt(profile_idx);
            // 페이징 기법
            int count = myPageService.review_count(profile_idx);
            paging.setTotalRecord(count);

            if (paging.getTotalRecord() <= paging.getNumPerPage()) {
                paging.setTotalPage(1);
            } else {
                paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
                if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
                    paging.setTotalPage(paging.getTotalPage() + 1);
                }
            }

            paging.setNowPage(Integer.parseInt(cPage));

            paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() - 1));

            paging.setBeginBlock((int) ((paging.getNowPage() - 1) 
                    / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);

            paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);

            if (paging.getEndBlock() > paging.getTotalPage()) {
                paging.setEndBlock(paging.getTotalPage());
            }

            //  DB 갔다오기
            List<ReviewVO> review_list = myPageService.reviewlist(profileIdx, paging.getNumPerPage(), paging.getOffset() );
            Map<String, Object> result = new HashMap<>();
            result.put("review_list", review_list);
            result.put("paging", paging);
            result.put("count", count);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.ok(0);
        }
    }

    @GetMapping("/questionDetail")
    public ResponseEntity<?> questionDetail(@RequestParam("question_idx") String question_idx) {
        QuestionVO res = myPageService.questionDetail(question_idx);
        return ResponseEntity.ok(res);
    }


    @PostMapping("/pay")
    public ResponseEntity<Integer> pay(@RequestHeader("Authorization") String token, @RequestParam String u_subs) {
        JwtDecode jwtDecode = new JwtDecode(token);
        String userId = jwtDecode.getUser_id();
        return ResponseEntity.ok(myPageService.pay(userId, u_subs));
    }
}
