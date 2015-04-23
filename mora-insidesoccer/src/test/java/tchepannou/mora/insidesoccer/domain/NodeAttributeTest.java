package tchepannou.mora.insidesoccer.domain;

import org.junit.Test;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.insidesoccer.dao.impl.IsMediaTypeDao;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class NodeAttributeTest {

    @Test
    public void testToPost() throws Exception {
        // Given
        Node node = new Node (1);
        NodeAttribute attr11 = new NodeAttribute(1, node, NodeAttribute.TITLE, "title1");
        NodeAttribute attr12 = new NodeAttribute(2, node, NodeAttribute.CONTENT, "<p>This is the content of<p>");
        NodeAttribute attr21 = new NodeAttribute(3, new Node(2), NodeAttribute.TITLE, "title2");

        Post post = new Post(1);

        // When
        NodeAttribute.toPost(Arrays.asList(attr11, attr12, attr21), post);

        // Then
        Post expected = new Post(1);
        expected.setTitle("title1");
        expected.setContent("<p>This is the content of<p>");
        expected.setSummary("This is the content of");
        assertThat(post, equalTo(expected));
    }

    @Test
    public void testToMedia_Image() throws Exception {
        // Given
        Node node = new Node (1);
        Media media = new Media(1);

        // When
        NodeAttribute.toMedia(Arrays.asList(
                new NodeAttribute(1, node, NodeAttribute.TITLE, "sundaylunch01.jpg"),
                new NodeAttribute(2, node, NodeAttribute.THUMBNAIL_URL, "/3/43/20243/sundaylunch01.jpg"),
                new NodeAttribute(3, node, NodeAttribute.URL, "/3/43/20243/sundaylunch01_IMG.jpg")
        ), media);

        // Then
        Media expected = new Media(1);
        expected.setTitle("sundaylunch01.jpg");
        expected.setUrl("/3/43/20243/sundaylunch01_IMG.jpg");
        expected.setThumbnailUrl("/3/43/20243/sundaylunch01.jpg");
        expected.setTypeId(IsMediaTypeDao.IMAGE);
        expected.setContentType("image/jpeg");
        assertThat(media, equalTo(expected));
    }

    @Test
    public void testToMedia_Oembed_YouTube() throws Exception {
        // Given
        Node node = new Node (1);
        Media media = new Media(1);

        // When
        NodeAttribute.toMedia(Arrays.asList(
                new NodeAttribute(1, node, NodeAttribute.NAME, "Xavi and Iniesta - Legendary Duo"),
                new NodeAttribute(2, node, NodeAttribute.DESCRIPTION, "http://www.facebook.com/SurenVardanyanEditing New comp about Xaviesta. Probably the best duo in history of football."),
                new NodeAttribute(3, node, NodeAttribute.URL, "http://www.youtube.com/watch?v=L9B7Vb8WmbA"),
                new NodeAttribute(4, node, NodeAttribute.IMAGE_URL, "http://i1.ytimg.com/vi/L9B7Vb8WmbA/mqdefault.jpg"),
                new NodeAttribute(5, node, NodeAttribute.OEMBED, "http://www.youtube.com/oembed?url=http%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DL9B7Vb8WmbA&format=json")
        ), media);

        // Then
        Media expected = new Media(1);
        expected.setTitle("Xavi and Iniesta - Legendary Duo");
        expected.setDescription("http://www.facebook.com/SurenVardanyanEditing New comp about Xaviesta. Probably the best duo in history of football.");
        expected.setUrl("http://www.youtube.com/watch?v=L9B7Vb8WmbA");
        expected.setImageUrl("http://i1.ytimg.com/vi/L9B7Vb8WmbA/mqdefault.jpg");
        expected.setTypeId(IsMediaTypeDao.VIDEO);
        expected.setOembed(true);
        assertThat(media, equalTo(expected));
    }


    @Test
    public void testToMedia_IsVideo() throws Exception {
        // Given
        Node node = new Node (1);
        Media media = new Media(1);

        // When
        NodeAttribute.toMedia(Arrays.asList(
                new NodeAttribute(1, node, NodeAttribute.NAME, "Xavi and Iniesta - Legendary Duo"),
                new NodeAttribute(2, node, NodeAttribute.DESCRIPTION, "http://www.facebook.com/SurenVardanyanEditing New comp about Xaviesta. Probably the best duo in history of football."),
                new NodeAttribute(3, node, NodeAttribute.URL, "http://www.insidesoccer.com/?isf=video&id=757870"),
                new NodeAttribute(4, node, NodeAttribute.IMAGE_URL, "https://admin.insidesoccer.com/is5-asset/0/70/757870/757870_thumbnail_url_1395256547525.jpg"),
                new NodeAttribute(5, node, NodeAttribute.INSIDESOCCER_TYPE, "video"),
                new NodeAttribute(6, node, NodeAttribute.INSIDESOCCER_ID, "757870")
        ), media);

        // Then
        Media expected = new Media(1);
        expected.setTitle("Xavi and Iniesta - Legendary Duo");
        expected.setDescription("http://www.facebook.com/SurenVardanyanEditing New comp about Xaviesta. Probably the best duo in history of football.");
        expected.setUrl("http://www.insidesoccer.com/?isf=video&id=757870");
        expected.setImageUrl("https://admin.insidesoccer.com/is5-asset/0/70/757870/757870_thumbnail_url_1395256547525.jpg");
        expected.setTypeId(IsMediaTypeDao.VIDEO);
        expected.setOembed(true);
        assertThat(media, equalTo(expected));
    }

    @Test
    public void testToMedia_IsAsb() throws Exception {
        // Given
        Node node = new Node (1);
        Media media = new Media(1);

        // When
        NodeAttribute.toMedia(Arrays.asList(
                new NodeAttribute(1, node, NodeAttribute.NAME, "Xavi and Iniesta - Legendary Duo"),
                new NodeAttribute(2, node, NodeAttribute.DESCRIPTION, "http://www.facebook.com/SurenVardanyanEditing New comp about Xaviesta. Probably the best duo in history of football."),
                new NodeAttribute(3, node, NodeAttribute.URL, "http://www.insidesoccer.com/?isf=video&id=757870"),
                new NodeAttribute(4, node, NodeAttribute.IMAGE_URL, "https://admin.insidesoccer.com/is5-asset/0/70/757870/757870_thumbnail_url_1395256547525.jpg"),
                new NodeAttribute(5, node, NodeAttribute.INSIDESOCCER_TYPE, "asb"),
                new NodeAttribute(6, node, NodeAttribute.INSIDESOCCER_ID, "757870")
        ), media);

        // Then
        Media expected = new Media(1);
        expected.setTitle("Xavi and Iniesta - Legendary Duo");
        expected.setDescription("http://www.facebook.com/SurenVardanyanEditing New comp about Xaviesta. Probably the best duo in history of football.");
        expected.setUrl("http://www.insidesoccer.com/?isf=video&id=757870");
        expected.setImageUrl("https://admin.insidesoccer.com/is5-asset/0/70/757870/757870_thumbnail_url_1395256547525.jpg");
        expected.setTypeId(IsMediaTypeDao.ASB);
        expected.setOembed(true);
        assertThat(media, equalTo(expected));
    }


    @Test
    public void testToMedia_Unknown() throws Exception {
        // Given
        Node node = new Node (1);
        Media media = new Media(1);

        // When
        NodeAttribute.toMedia(Arrays.asList(
                new NodeAttribute(1, node, NodeAttribute.NAME, "Xavi and Iniesta - Legendary Duo"),
                new NodeAttribute(2, node, NodeAttribute.DESCRIPTION, "http://www.facebook.com/SurenVardanyanEditing New comp about Xaviesta. Probably the best duo in history of football."),
                new NodeAttribute(3, node, NodeAttribute.URL, "http://www.insidesoccer.com/?isf=video&id=757870"),
                new NodeAttribute(4, node, NodeAttribute.IMAGE_URL, "https://admin.insidesoccer.com/is5-asset/0/70/757870/757870_thumbnail_url_1395256547525.jpg"),
                new NodeAttribute(5, node, NodeAttribute.INSIDESOCCER_TYPE, "article"),
                new NodeAttribute(6, node, NodeAttribute.INSIDESOCCER_ID, "757870")
        ), media);

        // Then
        Media expected = new Media(1);
        expected.setTitle("Xavi and Iniesta - Legendary Duo");
        expected.setDescription("http://www.facebook.com/SurenVardanyanEditing New comp about Xaviesta. Probably the best duo in history of football.");
        expected.setUrl("http://www.insidesoccer.com/?isf=video&id=757870");
        expected.setImageUrl("https://admin.insidesoccer.com/is5-asset/0/70/757870/757870_thumbnail_url_1395256547525.jpg");
        expected.setTypeId(IsMediaTypeDao.UNKNOWN);
        expected.setOembed(true);
        assertThat(media, equalTo(expected));
    }
}