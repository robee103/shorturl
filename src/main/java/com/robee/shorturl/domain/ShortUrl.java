package com.robee.shorturl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "url_dict")
@Entity(name = "url_dict")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(includeFieldNames = true)
public class ShortUrl {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

    @Column(name = "long_url")
    private String longUrl;

    @Column(name = "short_code")
    private String shortCode;
    
    @Column(name = "visit_count")
    private Long visitCount;
    
    
}
