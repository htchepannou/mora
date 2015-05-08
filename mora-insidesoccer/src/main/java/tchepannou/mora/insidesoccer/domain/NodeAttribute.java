package tchepannou.mora.insidesoccer.domain;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.domain.EventType;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.insidesoccer.dao.impl.IsMediaTypeDao;

import javax.activation.MimetypesFileTypeMap;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class NodeAttribute extends Attribute {
    //-- Attributes
    private static final Logger LOG = LoggerFactory.getLogger(NodeAttribute.class);

    public static final String CONTENT = "content";
    public static final String IMAGE_URL = "image_url";
    public static final String INSIDESOCCER_ID = "insidesoccer_id";
    public static final String INSIDESOCCER_TYPE = "insidesoccer_type";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String OEMBED = "oembed";
    public static final String SIZE = "SIZE";
    public static final String THUMBNAIL_URL = "thumbnail_url";
    public static final String TITLE = "title";
    public static final String LOCATION = "location";
    public static final String STREET = "street";
    public static final String CITY = "city";
    public static final String COUNTRY = "country";
    public static final String ZIP_CODE = "zip_code";
    public static final String STATE = "state";
    public static final String URL = "url";
    public static final String OPPONENT = "opponent";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String RSVP = "rsvp";
    public static final String EVENT_TYPE = "event_type";
    public static final String END_HOUR = "end_hour";
    public static final String END_TIME = "end_time";
    public static final String NOTES = "notes";


    public static final List<String> POST_ATTRIBUTES = Arrays.asList(TITLE, CONTENT);
    public static final List<String> MEDIA_ATTRIBUTES = Arrays.asList(NAME, TITLE, DESCRIPTION, SIZE, URL, THUMBNAIL_URL, IMAGE_URL, OEMBED, INSIDESOCCER_TYPE, INSIDESOCCER_ID);
    public static final List<String> EVENT_ATTRIBUTES = Arrays.asList(TITLE,
            LOCATION,
            STREET,
            CITY,
            COUNTRY,
            ZIP_CODE,
            STATE,
            URL,
            OPPONENT,
            HOUR,
            MINUTE,
            RSVP,
            EVENT_TYPE,
            END_HOUR,
            END_TIME,
            NOTES
    );

    private long nodeId;

    //-- Public
    public NodeAttribute(){
    }
    public NodeAttribute(long id, NodePartyRelationship node, String name, String value){
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

    public static void toMedia(Collection<NodeAttribute> attributes, Media media) {
        String isType = null;
        String isId = null;

        media.setTypeId(IsMediaTypeDao.UNKNOWN);

        for (NodeAttribute attr : attributes) {
            if (attr.getNodeId() != media.getId()) {
                continue;
            }

            String name = attr.getName();
            String value = attr.getValue();
            if (INSIDESOCCER_TYPE.equals(name)) {
                isType = value;
            } else if (INSIDESOCCER_ID.equals(name)) {
                isId = value;
            } else {
                mediaSetter(name, value, media);
            }
        }

        mediaISAttributes(isType, isId, media);
    }

    public static void toEvent(Collection<NodeAttribute> attributes, Event event){
        int startHour = 0;
        int endHour = 0;
        int startMinute = 0;
        int endMinute = 0;

        for (NodeAttribute attr : attributes) {
            if (attr.getNodeId() != event.getId()) {
                continue;
            }

            String name = attr.getName();
            String value = attr.getValue();
            if (HOUR.equals(name)){
                startHour = Integer.valueOf(value);
            } else if (MINUTE.equals(name)){
                startMinute = Integer.valueOf(value);
            } else if (END_HOUR.equals(name)){
                endHour = Integer.valueOf(value);
            } else if (END_TIME.equals(name)){
                endMinute = Integer.valueOf(value);
            } else{
                eventSetter(name, value, event);
            }
        }

        event.setStartDateTime(toDateTime(event.getStartDateTime(), startHour, startMinute));
        event.setEndDateTime(toDateTime(event.getEndDateTime(), endHour, endMinute));
    }

    private static Date toDateTime(Date date, int hour, int minute){
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, 0);
            return cal.getTime();
        } else {
            return null;
        }
    }

    //-- Getter/Setter
    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    //-- Private
    private static void mediaSetter(String name, String value, Media media){
        if (NAME.equalsIgnoreCase(name) || TITLE.equalsIgnoreCase(name)) {
            media.setTitle(value);
        } else if (DESCRIPTION.equalsIgnoreCase(name)) {
            media.setDescription(value);
        } else if (SIZE.equalsIgnoreCase(name)) {
            try {
                media.setSize(Long.parseLong(value));
            } catch (Exception e) {
                LOG.warn("Invalid size: " + value, e);
            }
        } else if (URL.equalsIgnoreCase(name)) {
            media.setUrl(value);
        } else if (IMAGE_URL.equals(name)) {
            media.setImageUrl(value);
        } else if (THUMBNAIL_URL.equals(name)) {
            media.setThumbnailUrl(value);
        } else if (OEMBED.equals(name)) {
            media.setOembed(!Strings.isNullOrEmpty(value));
        }
    }
    private static void mediaISAttributes(String isType, String isId, Media media){
        if (media.isOembed()) {
            media.setTypeId(IsMediaTypeDao.VIDEO);
        } else if (!Strings.isNullOrEmpty(isId)){
            if ("asb".equalsIgnoreCase(isType)){
                media.setTypeId(IsMediaTypeDao.ASB);
            } else if ("video".equalsIgnoreCase(isType)){
                media.setTypeId(IsMediaTypeDao.VIDEO);
            }

            media.setOembed(true);
        } else {
            MimetypesFileTypeMap mm = new MimetypesFileTypeMap();
            String contentType = mm.getContentType(media.getUrl());
            if (contentType != null){
                media.setContentType(contentType);
                if (contentType.startsWith("image/")) {
                    media.setTypeId(IsMediaTypeDao.IMAGE);
                } else if (contentType.startsWith("video/")) {
                    media.setTypeId(IsMediaTypeDao.VIDEO);
                }
            }
        }
    }

    private static void eventSetter(String name, String value, Event event){
        if (TITLE.equals(name)){
            event.setTitle(value);
        } else if (NOTES.equals(name)){
            event.setNotes(value);
        } else if (URL.equals(name)){
            event.setUrl(value);
        } else if (LOCATION.equals(name)){
            event.setLocation(value);
        } else if (RSVP.equals(name)){
            event.setRequiresRSVP("1".equals(value));
        } else if (EVENT_TYPE.equals(name)){
            if ("game".equals(value)){
                event.setTypeId(EventType.GAME);
            } else if ("practice".equals(value)){
                event.setTypeId(EventType.PRACTICE);
            } else {
                event.setTypeId(EventType.OTHER);
            }
        } else if (OPPONENT.equals(name)){
            event.setOpponent(value);
        }
    }
    private static LocalTime toLocalTime(int hour, int minutes){
        return hour>0 && minutes>=0 ? LocalTime.of(hour, minutes) : null;
    }
}
