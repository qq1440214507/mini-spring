package com.wy.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.wy.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{
    private final String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path,"Path must not null");
        this.path = path;
        this.classLoader = classLoader!=null?classLoader: ClassUtils.getDefaultClassLoader();
    }

    public ClassPathResource(String path) {
        this(path,null);
    }
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null){
            throw new FileNotFoundException(this.path+"cannot opened,because it not exist");
        }
        return is;
    }
}
