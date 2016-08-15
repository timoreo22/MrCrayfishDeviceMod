package com.mrcrayfish.device.programs.system;

import java.awt.Color;

import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Component;
import com.mrcrayfish.device.api.app.component.Button;
import com.mrcrayfish.device.api.app.component.ItemList;
import com.mrcrayfish.device.api.app.component.Label;
import com.mrcrayfish.device.api.app.component.Text;
import com.mrcrayfish.device.api.app.listener.ClickListener;
import com.mrcrayfish.device.api.app.renderer.ListItemRenderer;
import com.mrcrayfish.device.core.TaskBar;
import com.mrcrayfish.device.object.AppInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.nbt.NBTTagCompound;

public class ApplicationAppStore extends Application
{
	private Label appsLabel;
	private ItemList<AppInfo> apps;
	
	private Label appTitle;
	private Label appAuthor;
	private Text appDescription;
	private Button btnInstall;
	
	public ApplicationAppStore() 
	{
		super("app_store", "App Store", TaskBar.APP_BAR_GUI, 28, 30);
		this.setDefaultWidth(250);
		this.setDefaultHeight(150);
	}

	@Override
	public void init(int x, int y)
	{
		super.init(x, y);
		
		appsLabel = new Label("Application List", x, y, 5, 5);
		super.addComponent(appsLabel);
		
		apps = new ItemList<AppInfo>(x, y, 5, 18, 100, 6);
		apps.addItem(new AppInfo("Blah"));
		apps.addItem(new AppInfo("Blah"));
		apps.addItem(new AppInfo("Blah"));
		apps.setListItemRenderer(new ListItemRenderer<AppInfo>(20) {
			@Override
			public void render(AppInfo e, Gui gui, Minecraft mc, int x, int y, int width, int height, boolean selected) {
				if(selected)
					gui.drawRect(x, y, x + width, y + height, Color.DARK_GRAY.getRGB());
				else
					gui.drawRect(x, y, x + width, y + height, Color.GRAY.getRGB());
				e.renderIcon(mc, x + 3, y + 3);
				gui.drawString(mc.fontRendererObj, e.toString(), x + 20, y + 6, Color.WHITE.getRGB());
				
			}
		});
		apps.setClickListener(new ClickListener() {
			@Override
			public void onClick(Component c, int mouseButton) {
				AppInfo info = apps.getSelectedItem();
				if(info != null)
				{
					appTitle.setText(info.getName());
					appAuthor.setText(info.getAuthor());
					appDescription.setText(info.getDescription());
					btnInstall.enabled = true;
				}
				else
				{
					appTitle.setText("-");
					appAuthor.setText("-");
					appDescription.setText("-");
					btnInstall.enabled = false;
				}
			}
		});
		super.addComponent(apps);
		
		appTitle = new Label("", x, y, 130, 5);
		super.addComponent(appTitle);
		
		appAuthor = new Label("", x, y, 130, 16);
		super.addComponent(appAuthor);
		
		appDescription = new Text("", Minecraft.getMinecraft().fontRendererObj, x, y, 130, 35, 100);
		super.addComponent(appDescription);
		
		btnInstall = new Button("Install", x, y, 125, 100, 100, 20);
		super.addComponent(btnInstall);
	}

	@Override
	public void load(NBTTagCompound tagCompound) 
	{
		
	}

	@Override
	public void save(NBTTagCompound tagCompound) 
	{
		
	}
}
