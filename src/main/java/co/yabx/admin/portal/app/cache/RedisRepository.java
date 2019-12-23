package co.yabx.admin.portal.app.cache;

import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import co.yabx.admin.portal.app.kyc.entities.User;

@Repository
public class RedisRepository {
	private HashOperations hashOperations;

	private RedisTemplate redisTemplate;

	public RedisRepository(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	public void save(String key, User user) {
		hashOperations.put(key, user.getId(), user);
	}

	public List findAll() {
		return hashOperations.values("USER");
	}

	public User findById(String key, String userId) {
		return (User) hashOperations.get(key, userId);
	}

	public void update(String key, User user) {
		save(key, user);
	}

	public void delete(String key, Long userId) {
		hashOperations.delete(key, userId);
	}
}
