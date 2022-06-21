package sg.edu.np.mad.mad_assignment;


import java.io.Serializable;

public class Block implements Serializable {
    private Integer BlockNo;
    private String Name;
    private String Description;
    private String School;
    private String Type;

    public Block(){}

    public Block(Integer blockNo,String name, String description, String school, String type) {
        BlockNo = blockNo;
        Name = name;
        Description = description;
        School = school;
        Type = type;
    }

    public Integer getBlockNo() {
        return BlockNo;
    }

    public void setBlockNo(Integer blockNo) {
        BlockNo = blockNo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
