package com.example.shuafeia;

/**
 * Data model for each row of the RecyclerView
 */
class friend {

    // Member variables representing the title and information about the sport.
    private String name;
    private final int imageResource;

    friend(String name,int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }
    public int getImageResource() {return imageResource;}

    public String getName() {
        return name;
    }

}
