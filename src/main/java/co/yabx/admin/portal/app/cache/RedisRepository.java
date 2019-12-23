package co.yabx.admin.portal.app.cache;

import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.AuthInfo;

@Repository
public class RedisRepository {
	private HashOperations hashOperations;

	private RedisTemplate redisTemplate;

	public RedisRepository(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	public void save(String key, String hKey, AuthInfo value) {
		hashOperations.put(key, hKey, value);
	}

	public List findAll() {
		return hashOperations.values("USER");
	}

	public AuthInfo findById(String key, String userId) {
		return (AuthInfo) hashOperations.get(key, userId);
	}

	public void update(String key, String hKey, AuthInfo value) {
		save(key, hKey, value);
	}

	public void delete(String key, Long userId) {
		hashOperations.delete(key, userId);
	}
}
