import com.dqys.flowbusiness.orm.mapper.BusinessMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pan on 16-12-22.
 */
public class PublicBusinessTest extends baseTest{
   @Autowired
    private BusinessMapper businessMapper;
    //@Autowired
    //private BusinessObjReMapper re;
    @Test
    public void get(){
        businessMapper.get(1);
    }
}
