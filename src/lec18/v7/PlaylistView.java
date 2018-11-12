package lec18.v7;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlaylistView extends JPanel implements PlaylistObserver, ActionListener {

	private Playlist plist;
	private JPanel list_panel;
	
	public PlaylistView(Playlist plist) {
		this.plist = plist;
		plist.addObserver(this);
		
		setLayout(new BorderLayout());

		list_panel = new JPanel();
		list_panel.setLayout(new GridLayout(0,1));
		buildListPanel();
		add(new JScrollPane(list_panel), BorderLayout.CENTER);
		
		AddSongWidget add_song_widget = new AddSongWidget(plist);
		add(add_song_widget, BorderLayout.SOUTH);
		
		JButton shuffle_button = new JButton("Shuffle");
		shuffle_button.addActionListener(this);
		JPanel button_panel = new JPanel();
		button_panel.add(shuffle_button);
		add(button_panel, BorderLayout.NORTH);

	}

	@Override
	public void playlistChanged(Playlist changed_playlist) {
		list_panel.removeAll();
		buildListPanel();
		list_panel.revalidate();
	}
	
	private void buildListPanel() {
		for (int i=0; i<plist.getSize(); i++) {
			SongListingWidget song_listing = new SongListingWidget(plist, i);
			list_panel.add(song_listing);
		}				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		plist.shuffle();
	}
}
