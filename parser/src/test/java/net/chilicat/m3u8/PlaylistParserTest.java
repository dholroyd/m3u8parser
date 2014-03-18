package net.chilicat.m3u8;

import junit.framework.TestCase;

import java.util.List;

/**
 * @author feesh
 */
public class PlaylistParserTest extends TestCase {
    public void testPlaylistParser_CanParsePlaylistWithStreamInfo() throws Exception {
        Playlist parsedPlaylist = Playlist.parse(getClass().getResourceAsStream("/playlist_streaminfo.m3u8"));
        List<Element> playlistElements = parsedPlaylist.getElements();
        assertNotNull(playlistElements);

        Element firstPlaylistElement = playlistElements.get(0);
        assertNotNull(firstPlaylistElement);
    }

    public void testPlaylistParser_ParsedStreamInfoDataIsCorrect_Bandwidth() throws Exception {
        Playlist parsedPlaylist = Playlist.parse(getClass().getResourceAsStream("/playlist_streaminfo.m3u8"));
        List<Element> playlistElements = parsedPlaylist.getElements();
        Element firstPlaylistElement = playlistElements.get(0);

        PlaylistInfo playListInfo = firstPlaylistElement.getPlayListInfo();
        assertNotNull(playListInfo);
        assertEquals(playListInfo.getBandWitdh(), 796000);
    }

    public void testPlaylistParser_ParsedStreamInfoDataIsCorrect_Codecs() throws Exception {
        Playlist parsedPlaylist = Playlist.parse(getClass().getResourceAsStream("/playlist_streaminfo.m3u8"));
        List<Element> playlistElements = parsedPlaylist.getElements();
        Element firstPlaylistElement = playlistElements.get(0);

        PlaylistInfo playListInfo = firstPlaylistElement.getPlayListInfo();
        assertNotNull(playListInfo);
        assertEquals(playListInfo.getCodecs(), "avc1.77.30, mp4a.40.2");
    }

    public void testPlaylistParser_ParsedStreamInfoDataIsCorrect_ProgramId() throws Exception {
        Playlist parsedPlaylist = Playlist.parse(getClass().getResourceAsStream("/playlist_streaminfo.m3u8"));
        List<Element> playlistElements = parsedPlaylist.getElements();
        Element firstPlaylistElement = playlistElements.get(0);

        PlaylistInfo playListInfo = firstPlaylistElement.getPlayListInfo();
        assertNotNull(playListInfo);
        assertEquals(playListInfo.getProgramId(), 1);
    }

    public void testPlaylistParser_WillReportInvalidPlaylistStreamInfo() throws Exception {
        boolean failedCorrectly = false;
        try {
            Playlist.parse(getClass().getResourceAsStream("/playlist_invalid_streaminfo.m3u8"));
        } catch (ParseException e) {
            failedCorrectly = true;
        }

        assertTrue("Exception not thrown", failedCorrectly);

    }
}
