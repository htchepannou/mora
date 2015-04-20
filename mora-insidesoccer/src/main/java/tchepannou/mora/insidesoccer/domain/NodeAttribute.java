package tchepannou.mora.insidesoccer.domain;

import com.google.common.base.Preconditions;
import org.jsoup.Jsoup;
import tchepannou.mora.core.domain.Post;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class NodeAttribute extends Attribute {
    //-- Attributes
    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    public static final List<String> POST_ATTRIBUTES = Arrays.asList(TITLE, CONTENT);

    private long nodeId;

    public NodeAttribute(){
    }
    public NodeAttribute(long id, Node node, String name, String value){
        super(id, name, value);

        Preconditions.checkArgument(node != null, "node == null");
        Preconditions.checkArgument(node.getId()>0, "node.id <= 0");

        this.nodeId = node.getId();
    }

    //-- Public
    public static void toPost(Collection<NodeAttribute> attributes, Post post){
        for (NodeAttribute attr : attributes){
            if (attr.getNodeId() != post.getId()){
                continue;
            }
            String name = attr.getName();
            String value = attr.getValue();
            if (TITLE.equalsIgnoreCase(name)){
                post.setTitle(value);
            } else if (CONTENT.equalsIgnoreCase(name)){
                post.setContent(value);

                String text = Jsoup.parse(value).text();
                String summary = text.length()>255  ? text.substring(0, 255) + "..." : text;
                post.setSummary(summary);
            }
        }
    }

    //-- Getter/Setter
    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }
}
