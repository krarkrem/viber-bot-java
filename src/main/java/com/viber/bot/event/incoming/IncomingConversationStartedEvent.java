package com.viber.bot.event.incoming;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import com.viber.bot.event.Event;
import com.viber.bot.profile.UserProfile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.viber.bot.Preconditions.checkNotEmpty;

@Immutable
public class IncomingConversationStartedEvent extends IncomingEvent {

    private final UserProfile user;
    private final String type;
    private final long token;
	private final boolean subscribed;
 	
    @Nullable
    private final String context;

    @JsonCreator
    IncomingConversationStartedEvent(final @JsonProperty("type") @Nonnull String type,
                                     final @JsonProperty("user") @Nonnull UserProfile user,
                                     final @JsonProperty("context") String context,
                                     final @JsonProperty("message_token") long token,
                                     final @JsonProperty("timestamp") long timestamp,
                                     final @JsonProperty("subscribed") boolean subscribed) {
        super(Event.CONVERSATION_STARTED, timestamp);
        this.user = checkNotNull(user);
        this.type = checkNotEmpty(type);
        this.context = Strings.emptyToNull(context);
        this.token = token;
        this.subscribed = subscribed;
    }

    public String getType() {
        return type;
    }

    public long getToken() {
        return token;
    }

    public UserProfile getUser() {
        return user;
    }
	
	public boolean isSubscribed() {
    	return subscribed;
    }

    @Nullable
    public String getContext() {
        return context;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        IncomingConversationStartedEvent that = (IncomingConversationStartedEvent) o;

        if (token != that.token) return false;
        if (subscribed != that.subscribed) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return context != null ? context.equals(that.context) : that.context == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (int) (token ^ (token >>> 32));
        result = 31 * result + (subscribed ? 1 : 0);
        result = 31 * result + (context != null ? context.hashCode() : 0);
        return result;
    }
}
