package sg.edu.np.mad.mad_assignment;



public class Block {
    private Integer BlockNo;
    private String Name;
    private String Description;
    private String School;

    public Block(){};

    public Block(Integer blockNo,String name, String description, String school) {
        BlockNo = blockNo;
        Name = name;
        Description = description;
        School = school;
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
}
