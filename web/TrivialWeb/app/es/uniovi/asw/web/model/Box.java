package es.uniovi.asw.web.model;

import es.uniovi.asw.web.model.types.Color;

public class Box {

	private Integer id;
	private Color category;
	private Boolean isHeadquarter;

	public Box(Integer id) {

		this.setId(id);
		this.setIsHeadquarter(false);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Color getCategory() {
		return category;
	}

	public void setCategory(Color category) {
		this.category = category;
	}

	public Boolean getIsHeadquarter() {
		return isHeadquarter;
	}

	public void setIsHeadquarter(Boolean isHeadquarter) {
		this.isHeadquarter = isHeadquarter;
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
		Box other = (Box) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Box [id=" + id + "]";
	}
}