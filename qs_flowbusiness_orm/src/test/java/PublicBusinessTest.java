import com.dqys.flowbusiness.orm.mapper.PublicBusinessMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pan on 16-12-22.
 */
public class PublicBusinessTest extends baseTest{
   @Autowired
    private PublicBusinessMapper publicBusinessMapper;
    //@Autowired
    //private BusinessObjReMapper re;
    @Test
    public void get(){
        publicBusinessMapper.get(1);
    }
}
