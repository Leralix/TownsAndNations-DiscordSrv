package io.github.leralix.eventListener;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import io.github.leralix.lang.Lang;
import org.tan.api.events.*;

import java.awt.*;
import java.time.Instant;
import java.util.logging.Logger;

public class NewsBroadcast implements TanListener {

    private final Logger pluginLogger;
    private final DiscordSRV discordSrvApi;

    public NewsBroadcast(DiscordSRV discordSRV, Logger logger) {
        this.pluginLogger = logger;
        this.discordSrvApi = discordSRV;
    }

    @EventHandler
    public void onAttackDeclared(AttackDeclaredEvent event) {
        sendMessage(Lang.ATTACK_DECLARED_TITLE.get(), Lang.ATTACK_DECLARED.get(event.getAttackerTerritory().getName(), event.getDefenderTerritory().getName()));
    }

    @EventHandler
    public void onAttackWonByAttacker(AttackWonByAttackerEvent event) {
        String attackerTerritoryName = event.getAttackerTerritory().getName();
        String defenderTerritoryName = event.getDefenderTerritory().getName();
        sendMessage(Lang.ATTACK_WON_BY_ATTACKER_TITLE.get(), Lang.ATTACK_WON_BY_ATTACKER.get(attackerTerritoryName, defenderTerritoryName, attackerTerritoryName));
    }

    @EventHandler
    public void onAttackWonByDefender(AttackWonByAttackerEvent event) {
        String attackerTerritoryName = event.getAttackerTerritory().getName();
        String defenderTerritoryName = event.getDefenderTerritory().getName();
        sendMessage(Lang.ATTACK_WON_BY_DEFENDER_TITLE.get(), Lang.ATTACK_WON_BY_DEFENDER.get(attackerTerritoryName, defenderTerritoryName, defenderTerritoryName));
    }

    @EventHandler
    public void onDefenderAcceptDemands(AttackCancelledByDefenderEvent event) {
        sendMessage(Lang.TERRITORY_SURRENDER_TITLE.get(), Lang.TERRITORY_SURRENDER.get(event.getDefenderTerritory().getName(), event.getAttackerTerritory().getName()));
    }

    @EventHandler
    public void onDiplomacyProposalAccepted(DiplomacyProposalAcceptedEvent event) {
        sendMessage(Lang.NEWSLETTER_DIPLOMACY_PROPOSAL_ACCEPTED_TITLE.get(),
                Lang.NEWSLETTER_DIPLOMACY_PROPOSAL_ACCEPTED.get(
                        event.getReceivingTerritory().getName(),
                        event.getProposingTerritory().getName(),
                        event.getNewDiplomacy().toString().toLowerCase())
        );
    }

    @EventHandler
    public void onDiplomacyProposal(DiplomacyProposalEvent event) {
        sendMessage(Lang.NEWSLETTER_DIPLOMACY_PROPOSAL_TITLE.get(),
                Lang.NEWSLETTER_DIPLOMACY_PROPOSAL.get(
                        event.getReceivingTerritory().getName(),
                        event.getProposingTerritory().getName(),
                        event.getProposedDiplomacy())
        );
    }

    @EventHandler
    public void onPlayerJoinTown(PlayerJoinTownEvent event) {
        sendMessage(Lang.PLAYER_JOINED_TOWN_NEWSLETTER_TITLE.get(),
                Lang.PLAYER_JOINED_TOWN_NEWSLETTER.get(
                        event.getPlayer().getNameStored(),
                        event.getTown().getName()
                ));
    }

    @EventHandler
    public void onPlayerRequestJoinTown(PlayerJoinRequestEvent event){
        sendMessage(Lang.NEWSLETTER_PLAYER_APPLICATION_TITLE.get(), Lang.NEWSLETTER_PLAYER_APPLICATION.get(event.getPlayer().getNameStored(), event.getTown().getName()));
    }

    @EventHandler
    public void onRegionCreated(RegionCreatedEvent event) {
        sendMessage(Lang.REGION_CREATED_NEWSLETTER_TITLE.get(), Lang.REGION_CREATED_NEWSLETTER.get(event.getExecutor().getNameStored(), event.getRegion().getName()));
    }

    @EventHandler
    public void onRegionDeleted(RegionDeletedEvent event) {
        sendMessage(Lang.REGION_DELETED_NEWSLETTER_TITLE.get(), Lang.REGION_DELETED_NEWSLETTER.get(event.getExecutor().getNameStored(), event.getRegion().getName()));
    }

    @EventHandler
    public void onTerritoryIndependence(TerritoryIndependenceEvent event) {
        sendMessage(Lang.TOWN_INDEPENDENCE_NEWSLETTER_TITLE.get(), Lang.TOWN_INDEPENDENCE_NEWSLETTER.get(event.getTerritory().getName(), event.getFormerOverlord().getName()));
    }

    @EventHandler
    public void  onTerritoryVassalAccepted(TerritoryVassalAcceptedEvent event) {
        sendMessage(Lang.TOWN_JOIN_REGION_ACCEPTED_NEWSLETTER_TITLE.get(), Lang.TOWN_JOIN_REGION_ACCEPTED_NEWSLETTER.get(event.getTerritory().getName(), event.getNewOverlord().getName()));
    }

    @EventHandler
    public void onTerritoryVassalForced(TerritoryVassalForcedEvent event) {
        sendMessage(Lang.FORCED_VASSALAGE_NEWSLETTER_TITLE.get(), Lang.FORCED_VASSALAGE_NEWSLETTER.get(event.getTerritory().getName(), event.getNewOverlord().getName()));
    }

    public void onTerritoryVassalProposal(TerritoryVassalProposalEvent event) {
        sendMessage(Lang.NEWSLETTER_JOIN_REGION_PROPOSAL_TITLE.get(), Lang.NEWSLETTER_JOIN_REGION_PROPOSAL.get(event.getPotentialOverlord().getName(), event.getTerritory().getName()));
    }

    @EventHandler
    public void onTownCreated(TownCreatedEvent event) {
        sendMessage(Lang.TOWN_CREATED_NEWSLETTER_TITLE.get(), Lang.TOWN_CREATED_NEWSLETTER.get(event.getExecutor().getNameStored(), event.getTown().getName()));
    }

    @EventHandler
    public void onTownDeleted(TownDeletedEvent event) {
        sendMessage(Lang.TOWN_DELETED_NEWSLETTER_TITLE.get(), Lang.TOWN_DELETED_NEWSLETTER.get(event.getExecutor().getNameStored(), event.getTown().getName()));
    }

    @EventHandler
    public void onLandmarkClaimed(LandmarkClaimedEvent event) {
        sendMessage(Lang.LANDMARK_CLAIMED_NEWSLETTER_TITLE.get(), Lang.LANDMARK_CLAIMED_NEWSLETTER.get(event.getNewOwner().getName(), event.getLandmark().getName()));
    }

    @EventHandler
    public void onLandmarkUnclaimed(LandmarkUnclaimedEvent event) {
        sendMessage(Lang.LANDMARK_UNCLAIMED_NEWSLETTER_TITLE.get(), Lang.LANDMARK_UNCLAIMED_NEWSLETTER.get(event.getOldOwner().getName(), event.getLandmark().getName()));
    }

    private void sendMessage(String title, String description) {
        final var mainChannel = discordSrvApi.getMainTextChannel();
        if (mainChannel == null){
            pluginLogger.warning("DiscordSRV main channel was null, unable to send messages");
            return;
        }

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(title)
                .setDescription(description)
                .setColor(Color.CYAN)
                .setFooter("Towns and Nations - DiscordSRV", null)
                .setTimestamp(Instant.now());

        mainChannel.sendMessageEmbeds(embed.build()).queue();
    }

}
