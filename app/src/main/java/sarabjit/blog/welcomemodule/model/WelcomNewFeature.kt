package sarabjit.blog.groovytestapplication.whatsnew

data class WelcomNewFeature(
    val secondaryButtonText: String,
    val welcomeScreens: List<WelcomeScreensItem>,
    val primaryButtonText: String
)