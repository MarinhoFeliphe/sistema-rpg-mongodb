package com.feliphecosta.sistemarpgmongodb.race.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.feliphecosta.sistemarpgmongodb.skill.domain.Skill;
import com.feliphecosta.sistemarpgmongodb.util.Attributes;

@Document
public class Race implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private Attributes attributes;
	private String bonus;
	private String commonClasses;
	private Skill raceSkill;

	public Race() {}

	public Race(String name, Integer strength, Integer agility, Integer intelligence, Integer will, String bonus,
			String commonClasses, Skill raceSkill) {
		this.id = null;
		this.name = name;
		this.setAttributes(new Attributes(strength, agility, intelligence, will));
		this.bonus = bonus;
		this.commonClasses = commonClasses;
		this.raceSkill = raceSkill;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getCommonClasses() {
		return commonClasses;
	}

	public void setCommonClasses(String commonClasses) {
		this.commonClasses = commonClasses;
	}

	public Skill getRaceSkill() {
		return raceSkill;
	}

	public void setRaceSkill(Skill raceSkill) {
		this.raceSkill = raceSkill;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Race other = (Race) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
