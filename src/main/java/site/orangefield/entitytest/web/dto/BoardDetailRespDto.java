package site.orangefield.entitytest.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDetailRespDto {
    // dto를 만든 이유 : 엔티티를 리턴하지 않기 위해, 통신을 위해
    // private Board board; 이렇게 하면 안된다.
    // 서비스에서 필요한 정보를 딱 만들어 준다.

    private Integer id;
    private String title;
    private String content;
    private boolean isLove; // 모델에 적지 못한 것

    // 이 안에서만 쓰고 다른 데서 쓸 일이 없다면 내부 클래스로 만들자
    // class BoardDto {
    // private Integer id;
    // private String title;
    // private String content;
    // }
}
