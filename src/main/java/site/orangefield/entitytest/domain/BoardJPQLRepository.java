package site.orangefield.entitytest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import site.orangefield.entitytest.web.dto.BoardDetailRespDto;

@RequiredArgsConstructor
@Repository
public class BoardJPQLRepository {
    // EntityManager를 사용하겠다
    private final EntityManager em;

    // 원하는 결과 : 1, 제목1, 내용1, true(좋아요)
    public BoardDetailRespDto mFindDetail(Integer id) {
        String sql = "SELECT b.*, true FROM board b WHERE id = ?";
        Query query = em.createNativeQuery(sql)
                .setParameter(1, id);

        Object[] result = (Object[]) query.getSingleResult();
        Integer boardId = (Integer) result[0];
        String title = (String) result[1];
        String content = (String) result[2];
        boolean isLove = (Boolean) result[3];

        BoardDetailRespDto dto = new BoardDetailRespDto(boardId, title, content, isLove);

        return dto;
    }

    public List<BoardDetailRespDto> mFindAll() {
        List<BoardDetailRespDto> dtos = new ArrayList<>();

        String sql = "SELECT b.*, true FROM board b";
        Query query = em.createNativeQuery(sql);

        List<Object[]> results = (List<Object[]>) query.getResultList();

        for (Object[] result : results) {
            Integer boardId = (Integer) result[0];
            String title = (String) result[1];
            String content = (String) result[2];
            boolean isLove = (Boolean) result[3];
            BoardDetailRespDto dto = new BoardDetailRespDto(boardId, title, content, isLove);
            dtos.add(dto);
        }

        return dtos;
    }
}
