/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.polycreo.id;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

/**
 * TODO miyamoto.daisuke.
 */
public class DefaultIdExtractor implements IdExtractor {
	
	private static final Collection<String> ID_ANNOTATION_NAME = Arrays.asList(
			"javax.persistence.Id",
			"org.springframework.data.annotation.Id");
	
	
	@Override
	public Serializable getId(Object entity) {
		if (entity == null) {
			return null;
		}
		Class<?> c = entity.getClass();
		while (c != null && c != Object.class) {
			Field[] declaredFields = c.getDeclaredFields();
			for (Field field : declaredFields) {
				for (Annotation annotation : field.getAnnotations()) {
					if (ID_ANNOTATION_NAME.contains(annotation.annotationType().getName())) {
						field.setAccessible(true);
						try {
							return (Serializable) field.get(entity);
						} catch (Exception e) { // NOPMD
							// NOPMD ignore
						}
					}
				}
			}
			c = c.getSuperclass();
		}
		throw new IllegalArgumentException("ID annotation is not found.");
	}
}
