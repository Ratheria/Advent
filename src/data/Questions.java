package data;

public enum Questions
{
    NONE(false),
    INSTRUCTIONS(true),
    DRAGON(false),
    RESURRECT(true),
    SCOREQUIT(true),
    QUIT(true),
    READBLASTHINT(true);

    public final boolean serious;

    Questions (boolean serious)
    {
        this.serious = serious;
    }
}