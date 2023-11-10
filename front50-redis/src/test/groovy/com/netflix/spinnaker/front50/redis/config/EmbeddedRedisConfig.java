/*
 * Copyright 2019 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.front50.redis.config;

import com.netflix.spinnaker.front50.redis.RedisConfigurationProperties;
import com.netflix.spinnaker.kork.jedis.EmbeddedRedis;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class EmbeddedRedisConfig {
  @Bean(destroyMethod = "destroy")
  EmbeddedRedis embeddedRedis() {
    return EmbeddedRedis.embed();
  }

  @Bean
  @Primary
  RedisConfigurationProperties redisConfigurationProperties(EmbeddedRedis embeddedRedis) {
    RedisConfigurationProperties redisConfigurationProperties = new RedisConfigurationProperties();
    redisConfigurationProperties.setHost(embeddedRedis.getHost());
    redisConfigurationProperties.setPort(embeddedRedis.getPort());
    return redisConfigurationProperties;
  }
}
