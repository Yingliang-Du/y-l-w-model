/*
 * This file is part of
 *
 * Copyright (C) 2014-2015 The YLW-Java-Validation-Framework Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ylw.template.model.repository;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ylw.template.model.ModelConfig;
import com.ylw.template.model.entity.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ModelConfig.class)
public class PersonRepositoryIT {
	
	@Autowired
	private PersonRepository repository;

	@Test
	public void test() {
		Person person = new Person();
		person.setFirstName("Yingliang");
		person.setLastName("Du");
		
		assertThat(person.getId(), is(nullValue()));
		
		repository.save(person);
		assertThat(person.getId(), is(notNullValue()));
		
		Revision<Integer, Person> revision = repository.findLastChangeRevision(person.getId());
		assertNotNull("The last change revision should not be null", revision);
		assertThat(revision, is(notNullValue()));
	}

}
