package com.th.forge.albums.data.network.model.albumdetail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumResponse {

    @SerializedName("resultCount")
    @Expose
    private Long resultCount;
    @SerializedName("results")
    @Expose
    private List<AlbumResult> results = null;

    public Long getResultCount() {
        return resultCount;
    }

    public void setResultCount(Long resultCount) {
        this.resultCount = resultCount;
    }

    public List<AlbumResult> getResults() {
        return results;
    }

    public void setResults(List<AlbumResult> results) {
        this.results = results;
    }

}
