package com.ankur.blog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="comment")
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String content;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Post posts;
}
