package com.ankur.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name="posts")
@NoArgsConstructor
@Entity
public class Post {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="P_title", length=200)
	private String post_title;
	
	@Column(name="P_description", length=10000)
	private String post_Des;
	
	private String post_imagename;
	
	private Date post_date;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="posts", cascade=CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();
	
	}
