package com.abatplus.url_shortener.Repo;


import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.abatplus.url_shortener.urlDatabase.UrlData;

@Repository
public interface Repo extends JpaRepository<UrlData, Long>{
	
	Boolean existsByUrlId(char urlId);
	
	UrlData findAllByUrlId(char urlId);
		
	boolean existsById(Long id);
	
	UrlData save(UrlData urlData);
	
	List<UrlData> findAll();
	
//	@Query("SELECT new com.abatplus.url_shortener.urlDatabase.UrlDto(a.originUrl) FROM com.abatplus.url_shortener.urlDatabase.UrlData a")
//	List<UrlDto> findAllOriginUrl();
	
//	@Query("SELECT new com.abatplus.url_shortener.Repo.RepoCustom(a.originUrl) FROM com.abatplus.url_shortener.urlDatabase.UrlData a")
//	List<RepoCustom> findAllOriginUrl();
	
	@Transactional
	char deleteByUrlId(char urlId);
	
	
}
