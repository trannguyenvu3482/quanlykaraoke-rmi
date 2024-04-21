package iuh.fit.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LoaiHangHoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "char(6)")
	private String maLoaiHangHoa;

	@Column(columnDefinition = "nvarchar(30)")
	private String tenLoaiHangHoa;

	@Column(columnDefinition = "nvarchar(10)")
	private String donViTinh;

	public LoaiHangHoa() {
		super();
	}

	public LoaiHangHoa(String maLoaiHangHoa, String tenLoaihangHoa) {
		this.maLoaiHangHoa = maLoaiHangHoa;
		this.tenLoaiHangHoa = tenLoaihangHoa;
	}

	public LoaiHangHoa(String maLoaiHangHoa, String tenLoaihangHoa, String donViTinh) {
		this.maLoaiHangHoa = maLoaiHangHoa;
		this.tenLoaiHangHoa = tenLoaihangHoa;
		this.donViTinh = donViTinh;
	}

	public String getMaLoaiHangHoa() {
		return maLoaiHangHoa;
	}

	public void setMaLoaiHangHoa(String maLoaiHangHoa) {
		this.maLoaiHangHoa = maLoaiHangHoa;
	}

	public String getTenLoaiHangHoa() {
		return tenLoaiHangHoa;
	}

	public void setTenLoaihangHoa(String tenLoaihangHoa) {
		this.tenLoaiHangHoa = tenLoaihangHoa;
	}

	/**
	 * @return the donViTinh
	 */
	public String getDonViTinh() {
		return donViTinh;
	}

	/**
	 * @param donViTinh the donViTinh to set
	 */
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoaiHangHoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiHangHoa other = (LoaiHangHoa) obj;
		return Objects.equals(maLoaiHangHoa, other.maLoaiHangHoa);
	}

	@Override
	public String toString() {
		return "LoaiHangHoa [maLoaiHangHoa=" + maLoaiHangHoa + ", tenLoaihangHoa=" + tenLoaiHangHoa + "]";
	}

}
