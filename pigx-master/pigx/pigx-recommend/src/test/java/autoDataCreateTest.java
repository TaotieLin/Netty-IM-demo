import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.demo.PigxRecommendApplication;
import com.test.demo.dao.BrowseRecordsDao;
import com.test.demo.dao.NewsDao;
import com.test.demo.dao.TagDao;
import com.test.demo.dao.UserDao;
import com.test.demo.entity.BrowseRecordsEntity;
import com.test.demo.entity.NewsEntity;
import com.test.demo.entity.TagEntity;
import com.test.demo.entity.UserEntity;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lkd
 * @date 2020/9/12
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = PigxRecommendApplication.class)
public class autoDataCreateTest {

	@Resource
	private UserDao userDao;

	@Resource
	private NewsDao newsDao;

	@Resource
	private TagDao tagDao;

	@Resource
	private BrowseRecordsDao recordsDao;

	@Test
	public void dateCreateTest(){

		//生成基础标签五个
		System.out.println("开始生成标签");
		TagEntity tagEntity = new TagEntity();
		tagEntity.setName("军事");
		tagDao.insert(tagEntity);
		tagEntity.setId(null);
		tagEntity.setName("资讯");
		tagDao.insert(tagEntity);
		tagEntity.setName("搞笑");
		tagEntity.setId(null);
		tagDao.insert(tagEntity);
		tagEntity.setName("数码");
		tagEntity.setId(null);
		tagDao.insert(tagEntity);
		tagEntity.setName("科技");
		tagEntity.setId(null);
		tagDao.insert(tagEntity);

		//每个标签生成100篇资讯
		System.out.println("开始生成资讯");
		List<TagEntity> tagEntities = tagDao.selectList(new QueryWrapper<>());
		for (TagEntity entity : tagEntities) {
			NewsEntity newsEntity = new NewsEntity();
			newsEntity.setTagId(entity.getId());
			for (int i = 0; i < 100; i++) {
				String s = "第"+(i+1)+"篇"+entity.getName()+"类资讯！";
				newsEntity.setTitle(s);
				newsEntity.setContent(s);
				newsEntity.setPhoto("photoUrl");
				newsDao.insert(newsEntity);
				newsEntity.setId(null);
			}
		}
		//生成三个用户，阅读A标签用户，阅读B标签用户，空浏览记录用户
		System.out.println("开始生成用户");
		UserEntity userEntity = new UserEntity();
		userEntity.setName("A用户");
		userEntity.setToken("A");
		userDao.insert(userEntity);
		userEntity.setId(null);
		userEntity.setName("B用户");
		userEntity.setToken("B");
		userDao.insert(userEntity);
		userEntity.setId(null);
		userEntity.setName("C用户");
		userEntity.setToken("C");
		userDao.insert(userEntity);
		createBrowseRecords();

	}
	@Test
	public void createBrowseRecords() {
		//生成三个用户的浏览记录
		System.out.println("开始生成浏览记录");
		List<UserEntity> userEntities = userDao.selectList(new QueryWrapper<>());
		for (int i = 0; i < userEntities.size(); i++) {
			BrowseRecordsEntity browseRecordsEntity = new BrowseRecordsEntity();
			if (i != 2){
				for (int j = 0; j < 50; j++) {
					browseRecordsEntity.setNewsId(j+1L);
					browseRecordsEntity.setTagId(i+1L);
					browseRecordsEntity.setUserId(userEntities.get(i).getId());
					recordsDao.insert(browseRecordsEntity);
					browseRecordsEntity.setId(null);
				}
			}
		}
	}

}
