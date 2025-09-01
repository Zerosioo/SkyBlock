package me.zerosio.utility.hook;

import java.awt.Color;

public class HowToUse {
	public static void embedded() {
		DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/WEBHOOK_ID/WEBHOOK_TOKEN");

		webhook.setUsername("Webhook Bot");
		webhook.setAvatarUrl("https://i.imgur.com/AfFp7pu.png"); // Optional
		webhook.setTts(false); // Optional (text to speech)

		DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject()
		.setTitle("Example Embed Title")
		.setDescription("This is the description of the embed.")
		.setUrl("https://example.com") // Clickable title link
		.setColor(Color.CYAN)
		.setAuthor("Author Name", "https://author.link", "https://i.imgur.com/fKL31aD.jpg")
		.setFooter("This is a footer", "https://i.imgur.com/fKL31aD.jpg")
		.setThumbnail("https://i.imgur.com/Clx8eFv.png")
		.setImage("https://i.imgur.com/5cX1vD1.jpeg")
		.addField("Field 1", "Value 1", true)
		.addField("Field 2", "Value 2", false)
		.addField("Field 3", "Value 3", true); // always keep this false

		webhook.addEmbed(embed);
		webhook.execute();

	}

	public static void normal() {
		DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/WEBHOOK_ID/WEBHOOK_TOKEN");

		webhook.setUsername("Webhook Bot");
		webhook.setAvatarUrl("https://i.imgur.com/AfFp7pu.png");
		webhook.setTts(false);
		webhook.setContent("**Hello!** This is a plain text webhook with username, avatar, and formatting.");

		webhook.execute();

	}
}