package com.enities;

import java.util.UUID;

public class Film {
	public Film()
	{
		title = "";
		description = "";
		rating = new Rating(0);
		id = UUID.randomUUID();
	}

	private UUID id;

	private String title;

	private String description;

	private Rating rating;

	public Film(String title , String description , Rating rating)
	{
		this.title = title;
		this.description = description;
		this.rating = rating;
		id = UUID.randomUUID();
	}

	public UUID getId()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Rating getRating()
	{
		return rating;
	}

	public void setRating(Rating rating)
	{
		this.rating = rating;
	}
}