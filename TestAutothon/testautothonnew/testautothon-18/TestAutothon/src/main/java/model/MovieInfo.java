package model;

import Base.Device;
import lombok.Data;
import lombok.NonNull;

@Data
public class MovieInfo {
    @NonNull
    String movieName;
    @NonNull
    String wikiUrl;
    String wikiDirectorName;
    String imdbDirectorName;
    String imdbUrl;
    String mode;
    Device device;

    public String toString() {
        return "##movieName=" + this.getMovieName() + ",##wikiUrl=" + this.getWikiUrl() + ",## wikiDirectorName=" + this.getWikiDirectorName() + ",## imdbDirectorName=" + this.getImdbDirectorName() + ",## imdbUrl=" + this.getImdbUrl() + ",## mode=" + this.getMode() + ",## device=" + this.getDevice();
    }
}
