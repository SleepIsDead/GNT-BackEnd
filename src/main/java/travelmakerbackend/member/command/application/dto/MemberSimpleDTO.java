package travelmakerbackend.member.command.application.dto;

public class MemberSimpleDTO {

    private long memberId;

    private String nickname;

    private String imageSource;

    private String linkToMyPage;


    public MemberSimpleDTO() {}

    public MemberSimpleDTO(long memberId, String nickname, String imageSource) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.imageSource = imageSource;
    }

    public MemberSimpleDTO(long memberId, String nickname, String imageSource, String linkToMyPage) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.linkToMyPage = linkToMyPage;
        this.imageSource = imageSource;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLinkToMyPage() {
        return linkToMyPage;
    }

    public void setLinkToMyPage(String linkToMyPage) {
        this.linkToMyPage = linkToMyPage;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    @Override
    public String toString() {
        return "MemberSimpleDTO{" +
            "memberId=" + memberId +
            ", nickname='" + nickname + '\'' +
            ", imageSource=" + imageSource +
            ", linkToMyPage='" + linkToMyPage + '\'' +
            '}';
    }
}
