package com.ego.document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="goods")
public class Goods implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Indexed(unique=true)
	private String _id;
    private String name;
    private String fullname;
    private String description;
    private Integer num;
    private BigDecimal price;
    private BigDecimal sale;
    private BigDecimal jinprice;
    private String type;
    private String store;
    private String coverimg;
    private Integer lock;
    private Date createdate;
    private Date modifidate;
    
    //服装    美妆
    private String size;
    
	private String color;
	//电子
	private String rom;//内存
	//家电
	private String gonglv;
	//食品
	private String weight;
	
	
	public Integer getLock() {
		return lock;
	}
	public void setLock(Integer lock) {
		this.lock = lock;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getSale() {
		return sale;
	}
	public void setSale(BigDecimal sale) {
		this.sale = sale;
	}
	public BigDecimal getJinprice() {
		return jinprice;
	}
	public void setJinprice(BigDecimal jinprice) {
		this.jinprice = jinprice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getCoverimg() {
		return coverimg;
	}
	public void setCoverimg(String coverimg) {
		this.coverimg = coverimg;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getModifidate() {
		return modifidate;
	}
	public void setModifidate(Date modifidate) {
		this.modifidate = modifidate;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getRom() {
		return rom;
	}
	public void setRom(String rom) {
		this.rom = rom;
	}
	public String getGonglv() {
		return gonglv;
	}
	public void setGonglv(String gonglv) {
		this.gonglv = gonglv;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Goods(String _id, String name, String fullname, String description, Integer num, BigDecimal price,
			BigDecimal sale, BigDecimal jinprice, String type, String store, String coverimg, Date createdate,
			Date modifidate, String size, String color, String rom, String gonglv, String weight, Integer lock) {
		super();
		this._id = _id;
		this.name = name;
		this.fullname = fullname;
		this.description = description;
		this.num = num;
		this.price = price;
		this.sale = sale;
		this.jinprice = jinprice;
		this.type = type;
		this.store = store;
		this.coverimg = coverimg;
		this.createdate = createdate;
		this.modifidate = modifidate;
		this.size = size;
		this.color = color;
		this.rom = rom;
		this.gonglv = gonglv;
		this.weight = weight;
		this.lock = lock;
	}
	@Override
	public String toString() {
		return "Goods [_id=" + _id + ", name=" + name + ", fullname=" + fullname + ", description=" + description
				+ ", num=" + num + ", price=" + price + ", sale=" + sale + ", jinprice=" + jinprice + ", type=" + type
				+ ", store=" + store + ", coverimg=" + coverimg + ", createdate=" + createdate + ", modifidate="
				+ modifidate + ", size=" + size + ", color=" + color + ", rom=" + rom + ", gonglv=" + gonglv
				+ ", weight=" + weight + ", lock=" + lock + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((coverimg == null) ? 0 : coverimg.hashCode());
		result = prime * result + ((createdate == null) ? 0 : createdate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((gonglv == null) ? 0 : gonglv.hashCode());
		result = prime * result + ((jinprice == null) ? 0 : jinprice.hashCode());
		result = prime * result + ((lock == null) ? 0 : lock.hashCode());
		result = prime * result + ((modifidate == null) ? 0 : modifidate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((rom == null) ? 0 : rom.hashCode());
		result = prime * result + ((sale == null) ? 0 : sale.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((store == null) ? 0 : store.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Goods other = (Goods) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (coverimg == null) {
			if (other.coverimg != null)
				return false;
		} else if (!coverimg.equals(other.coverimg))
			return false;
		if (createdate == null) {
			if (other.createdate != null)
				return false;
		} else if (!createdate.equals(other.createdate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (gonglv == null) {
			if (other.gonglv != null)
				return false;
		} else if (!gonglv.equals(other.gonglv))
			return false;
		if (jinprice == null) {
			if (other.jinprice != null)
				return false;
		} else if (!jinprice.equals(other.jinprice))
			return false;
		if (lock == null) {
			if (other.lock != null)
				return false;
		} else if (!lock.equals(other.lock))
			return false;
		if (modifidate == null) {
			if (other.modifidate != null)
				return false;
		} else if (!modifidate.equals(other.modifidate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (rom == null) {
			if (other.rom != null)
				return false;
		} else if (!rom.equals(other.rom))
			return false;
		if (sale == null) {
			if (other.sale != null)
				return false;
		} else if (!sale.equals(other.sale))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (store == null) {
			if (other.store != null)
				return false;
		} else if (!store.equals(other.store))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	
    
}
