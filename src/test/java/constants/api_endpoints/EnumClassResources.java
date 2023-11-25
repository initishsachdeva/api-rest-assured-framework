package constants.api_endpoints;

//enum is special class in java which has collection of constants or  methods
public enum EnumClassResources {
    addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    updatePlaceAPI("/maps/api/place/update/json"),
    deletePlaceAPI("/maps/api/place/delete/json");
    private String resource;

    EnumClassResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }


}
