package site.orangefield.entitytest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import site.orangefield.entitytest.web.dto.BoardDetailRespDto;

@RequiredArgsConstructor
@Repository
public class BoardJPQLRepository {
    // EntityManager를 사용하겠다
    private final EntityManager em;

    // createQuery : DB 모델과 타입이 같아야 한다 -> 차라리 BoardRepository를 사용하자
    // createNativeQuery : 진짜 내 맘대로 쿼리(PreparedStatement)

    // 원하는 결과 : 1, 제목1, 내용1, true(좋아요)
    public BoardDetailRespDto mFindDetail(Integer id) {
        String sql = "SELECT b.*, true FROM board b WHERE id = ?";
        Query query = em.createNativeQuery(sql)
                .setParameter(1, id);

        Object[] result = (Object[]) query.getSingleResult(); // getSingleResult : 쿼리를 DB를 향해 버퍼로 쏘는 것
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

    public List<BoardDetailRespDto> mFindAllQLRM() {
        String sql = "SELECT b.*, true FROM board b";
        Query query = em.createNativeQuery(sql);

        JpaResultMapper mapper = new JpaResultMapper();
        List<BoardDetailRespDto> dtos = mapper.list(query, BoardDetailRespDto.class);

        return dtos;
    }
}
