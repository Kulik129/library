package com.example.models;

import lombok.Data;
import java.util.List;

@Data
public class Book {
    private String kind;
    private int totalItems;
    private List<Item> items;

    @Data
    public static class Item {
        private String kind;
        private String id;
        private String etag;
        private String selfLink;
        private VolumeInfo volumeInfo;
        private SaleInfo saleInfo;
        private AccessInfo accessInfo;
        private SearchInfo searchInfo;
    }

    @Data
    public static class VolumeInfo {
        private String title;
        private List<String> authors;
        private String publisher;
        private String publishedDate;
        private String description;
        private List<IndustryIdentifier> industryIdentifiers;
        private ReadingModes readingModes;
        private int pageCount;
        private String printType;
        private List<String> categories;
        private double averageRating;
        private int ratingsCount;
        private String maturityRating;
        private boolean allowAnonLogging;
        private String contentVersion;
        private PanelizationSummary panelizationSummary;
        private ImageLinks imageLinks;
        private String language;
        private String previewLink;
        private String infoLink;
        private String canonicalVolumeLink;
    }

    @Data
    public static class IndustryIdentifier {
        private String type;
        private String identifier;
    }

    @Data
    public static class ReadingModes {
        private boolean text;
        private boolean image;
    }

    @Data
    public static class PanelizationSummary {
        private boolean containsEpubBubbles;
        private boolean containsImageBubbles;
    }

    @Data
    public static class ImageLinks {
        private String smallThumbnail;
        private String thumbnail;
    }

    @Data
    public static class SaleInfo {
        private String country;
        private String saleability;
        private boolean isEbook;
        private ListPrice listPrice;
        private RetailPrice retailPrice;
        private String buyLink;
        private List<Offer> offers;
    }

    @Data
    public static class ListPrice {
        private double amount;
        private String currencyCode;
    }

    @Data
    public static class RetailPrice {
        private double amount;
        private String currencyCode;
    }

    @Data
    public static class Offer {
        private int finskyOfferType;
        private Price listPrice;
        private Price retailPrice;
    }

    @Data
    public static class Price {
        private long amountInMicros;
        private String currencyCode;
    }

    @Data
    public static class AccessInfo {
        private String country;
        private String viewability;
        private boolean embeddable;
        private boolean publicDomain;
        private String textToSpeechPermission;
        private Epub epub;
        private Pdf pdf;
        private String webReaderLink;
        private String accessViewStatus;
        private boolean quoteSharingAllowed;
    }

    @Data
    public static class Epub {
        private boolean isAvailable;
        private String acsTokenLink;
    }

    @Data
    public static class Pdf {
        private boolean isAvailable;
        private String acsTokenLink;
    }

    @Data
    public static class SearchInfo {
        private String textSnippet;
    }
}

