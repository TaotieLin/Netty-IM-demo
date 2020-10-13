package com.pig4cloud.pigx.mp.feign;

import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.security.annotation.EnablePigxFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lkd
 * @date 2020/9/18
 */

@FeignClient(name = "pigx-oa-platform")
public interface OaServer {

	@GetMapping("/leave-bill/page")
	R page();

}
