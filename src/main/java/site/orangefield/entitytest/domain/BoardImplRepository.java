package site.orangefield.entitytest.domain;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import site.orangefield.entitytest.web.dto.BoardDetailRespDto;

@RequiredArgsConstructor
@Repository
public class BoardImplRepository {
    // EntityManager를 사용하겠다
    private final EntityManager em;

    // 원하는 결과 : 1, 제목1, 내용1, true(좋아요)
    public BoardDetailRespDto mFindDetail(Integer id) {
        String sql = "SELECT b.*, true FROM board b WHERE id = ?";
        TypedQuery<BoardDetailRespDto> query = em.createQuery(sql, BoardDetailRespDto.class)
                .setParameter(1, id);
        BoardDetailRespDto dto = query.getSingleResult();
        return dto;
    }
}
