package com.ecxfoi.wbl.wienerbergerfrontend.api;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class JwtAuthInterceptor implements Interceptor
{
    private String jwtToken;

    @Inject
    public JwtAuthInterceptor()
    {
    }

    public void setJwtToken(String jwtToken)
    {
        this.jwtToken = jwtToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder();

        if (!StringUtils.isEmpty(jwtToken))
        {
            builder.addHeader("Authorization", String.format("Bearer %s", jwtToken));
        }

        Request request = builder.build();
        return chain.proceed(request);
    }
}

