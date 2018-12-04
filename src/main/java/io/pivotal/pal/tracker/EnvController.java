package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    public String vport;
    public String vmemlimit;
    public String vcfinstind;
    public String vcfinstaddr;


    @GetMapping("/env")
    public Map<String, String> getEnv()
    {
       Map<String,String> setEnv = new HashMap<>();

       setEnv.put("PORT",vport);
        setEnv.put("MEMORY_LIMIT",vmemlimit);
        setEnv.put("CF_INSTANCE_INDEX",vcfinstind);
        setEnv.put("CF_INSTANCE_ADDR",vcfinstaddr);

        System.out.println("PORT => " + vport);

        return setEnv;

    }

    public EnvController(@Value("${PORT:8025}") String port,
                         @Value("${MEMORY_LIMIT:NOT SET}") String Ml,
                         @Value("${CF_INSTANCE_INDEX:NOT SET}") String Cii,
                         @Value("${CF_INSTANCE_ADDR:NOT SET}") String Cia)
    {

this.vport = port;
this.vmemlimit=Ml;

this.vcfinstind=Cii;
this.vcfinstaddr=Cia;

    }
}
