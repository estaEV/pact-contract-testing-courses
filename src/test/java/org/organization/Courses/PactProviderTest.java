package org.organization.Courses;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import org.organization.controller.AllCourseData;
import org.organization.repository.CoursesRepository;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.StateChangeAction;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("CoursesCatalogue")
//@PactFolder("src/main/java/pacts")
@PactBroker(url="", authentication = @PactBrokerAuth(token=""))
public class PactProviderTest {

	@LocalServerPort
	public int port;

	@Autowired
	CoursesRepository repository;

	@TestTemplate
	@ExtendWith(PactVerificationInvocationContextProvider.class)
	public void pactVerificationTest(PactVerificationContext context) {
		context.verifyInteraction();
	}

	@BeforeEach
	public void setup(PactVerificationContext context) {
		context.setTarget(new HttpTestTarget("localhost", port));
	}

	@State(value = "Courses exist", action = StateChangeAction.SETUP)
	public void coursesExist() {

	}

	@State(value = "Courses exist", action = StateChangeAction.TEARDOWN)
	public void coursesExistTeardown() {

	}

	@State(value = "Course Appium exist", action = StateChangeAction.SETUP)
	public void courseAppiumExist() {

	}

	@State(value = "Course Appium exist", action = StateChangeAction.TEARDOWN)
	public void courseAppiumExistTeardown() {

	}

	@State(value = "Course Appium exist", action = StateChangeAction.SETUP)
	public void courseAppiumDoesNotExist() {

	}

	@State(value = "Course Appium does not exist", action = StateChangeAction.SETUP)
	public void courseAppiumDoesNotExistSetup(Map<String, Object> params) {
		String name = String.valueOf(params.get("name"));
		Optional<AllCourseData> del = repository.findById(name);
		if (del.isPresent()) {
			repository.deleteById(name);
		}
	}

	@State(value = "Course Appium does not exist", action = StateChangeAction.TEARDOWN)
	public void courseAppiumDoesNotExistTeardown(Map<String, Object> params) {
		String name = String.valueOf(params.get("name"));
		Optional<AllCourseData> del = repository.findById(name);
				if (!del.isPresent()) {
					AllCourseData allCourseData = new AllCourseData();
					allCourseData.setCourse_name(name);
					allCourseData.setCategory("mobile");
					allCourseData.setPrice(120);
					allCourseData.setId("12");
					repository.save(allCourseData);
				}
	}

}
