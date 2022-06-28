package ejb;

import javax.enterprise.context.ApplicationScoped;

import dto.UtilityDto;

@ApplicationScoped
public class Service {
	public UtilityDto compute() {
		return new UtilityDto();
	}
}
