package me.zhoubl.pay.common.web.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;

import me.zhoubl.pay.common.utils.UtilServlet;

public class ShiroSessionDao extends AbstractSessionDAO {

	private RedisTemplate redisTemplate;

	private String keyPrefix = "shiro_redis_session:";

	private int expire = 0;

	private String getKey(Serializable sessionId) {
		return this.keyPrefix + sessionId;
	}

	@Override
	protected Serializable doCreate(Session session) {
		HttpServletRequest request = UtilServlet.getRequest();
		if (request != null) {
			String uri = request.getServletPath();
			// 如果是静态文件，则不创建SESSION
			if (UtilServlet.isStaticFile(uri)) {
				return null;
			}
		}
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.update(session);
		return sessionId;
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		if (session == null || session.getId() == null) {
			return;
		}

		HttpServletRequest request = UtilServlet.getRequest();
		if (request != null) {
			String uri = request.getRequestURI();
			// 如果是静态文件，则不更新SESSION
			if (UtilServlet.isStaticFile(uri)) {
				return;
			}
		}

		redisTemplate.opsForValue().set(getKey(session.getId()), session, expire, TimeUnit.MILLISECONDS);
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session s = null;
		HttpServletRequest request = UtilServlet.getRequest();
		if (request != null) {
			String uri = request.getRequestURI();
			// 如果是静态文件，则不获取SESSION
			if (UtilServlet.isStaticFile(uri)) {
				return null;
			}
			s = (Session) request.getAttribute("session_" + sessionId);
		}
		if (s != null) {
			return s;
		}

		Session session = (Session) redisTemplate.opsForValue().get(getKey(sessionId));

		if (request != null && session != null) {
			request.setAttribute("session_" + sessionId, session);
		}

		return session;
	}

	@Override
	public Session readSession(Serializable sessionId) throws UnknownSessionException {
		try {
			return super.readSession(sessionId);
		} catch (UnknownSessionException e) {
			return null;
		}
	}

	@Override
	public void delete(Session session) {

		if (session == null || session.getId() == null) {
			return;
		}
		redisTemplate.delete(getKey(session.getId()));

	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();
		Set<String> keys = redisTemplate.keys(this.keyPrefix + "*");
		if (keys != null && keys.size() > 0) {
			for (String key : keys) {
				Session s = (Session) redisTemplate.opsForValue().get(key);
				sessions.add(s);
			}
		}

		return sessions;
	}

	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

}
