package net.chilicat.m3u8;

import java.net.URI;

/**
 * @author dkuffner
 */
final class ElementImpl implements Element {
    private final long mediaSequence;
    private final PlaylistInfo playlistInfo;
    private final EncryptionInfo encryptionInfo;
    private final int duration;
    private final URI uri;
    private final String title;
    private final long programDate;

    public ElementImpl(long mediaSequence, PlaylistInfo playlistInfo, EncryptionInfo encryptionInfo, int duration, URI uri, String title, long programDate) {
        this.mediaSequence = mediaSequence;
        if (uri == null) {
            throw new NullPointerException("uri");
        }

        if (duration < -1) {
            throw new IllegalArgumentException();
        }
        if (playlistInfo != null && encryptionInfo != null) {
            throw new IllegalArgumentException("Element cannot be a encrypted playlist.");
        }
        this.playlistInfo = playlistInfo;
        this.encryptionInfo = encryptionInfo;
        this.duration = duration;
        this.uri = uri;
        this.title = title;
        this.programDate = programDate;
    }

    public long getMediaSequence() {
        return mediaSequence;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public URI getURI() {
        return uri;
    }

    public boolean isEncrypted() {
        return encryptionInfo != null;
    }

    public boolean isPlayList() {
        return playlistInfo != null;
    }

    public boolean isMedia() {
        return playlistInfo == null;
    }

    public EncryptionInfo getEncryptionInfo() {
        return encryptionInfo;
    }

    public PlaylistInfo getPlayListInfo() {
        return playlistInfo;
    }

    public long getProgramDate() {
        return programDate;
    }

    @Override
    public String toString() {
        return "ElementImpl{" +
                "playlistInfo=" + playlistInfo +
                ", encryptionInfo=" + encryptionInfo +
                ", duration=" + duration +
                ", uri=" + uri +
                ", title='" + title + '\'' +
                '}';
    }

    static final class PlaylistInfoImpl implements PlaylistInfo {
        private final int programId;
        private final int bandWidth;
        private final String codec;
        private final String resolution;

        public PlaylistInfoImpl(int programId, int bandWidth, String codec, String resolution) {
            this.programId = programId;
            this.bandWidth = bandWidth;
            this.codec = codec;
            this.resolution = resolution;
        }

        public int getProgramId() {
            return programId;
        }

        public int getBandWitdh() {
            return bandWidth;
        }

        public String getCodecs() {
            return codec;
        }

        public String getResolution() {
            return resolution;
        }

        @Override
        public String toString() {
            return "PlaylistInfoImpl{" +
                    "programId=" + programId +
                    ", bandWidth=" + bandWidth +
                    ", codec='" + codec + '\'' +
                    ", resolution=" + resolution +
                    '}';
        }
    }

    static final class EncryptionInfoImpl implements EncryptionInfo {
        private final URI uri;
        private final String method;

        public EncryptionInfoImpl(URI uri, String method) {
            this.uri = uri;
            this.method = method;
        }

        public URI getURI() {
            return uri;
        }

        public String getMethod() {
            return method;
        }

        @Override
        public String toString() {
            return "EncryptionInfoImpl{" +
                    "uri=" + uri +
                    ", method='" + method + '\'' +
                    '}';
        }
    }
}
