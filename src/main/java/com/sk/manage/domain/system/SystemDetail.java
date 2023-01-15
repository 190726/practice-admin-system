package com.sk.manage.domain.system;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class SystemDetail {
	
	@Column(name = "url_info")
	private String urlInfo;
	
	@Column(name = "server_info")
	private String serverInfo;
}