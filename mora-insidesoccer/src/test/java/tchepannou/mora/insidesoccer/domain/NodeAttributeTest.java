package tchepannou.mora.insidesoccer.domain;

import org.junit.Test;
import tchepannou.mora.core.domain.Post;

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
        NodeAttribute attr21 = new NodeAttribute(2, new Node(2), NodeAttribute.TITLE, "title2");

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
}