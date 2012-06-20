package net.sgoliver.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class OverlayMapa extends Overlay {
	
	private Double latitud = -33.42*1E6;
	private Double longitud = -70.64*1E6;
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) 
	{
		Projection projection = mapView.getProjection();
		GeoPoint geoPoint = 
			new GeoPoint(latitud.intValue(), longitud.intValue());
		
		if (shadow == false) 
		{
			Point centro = new Point();
			projection.toPixels(geoPoint, centro);

			//Pincel de dibujo
			Paint p = new Paint();
			p.setColor(Color.BLUE);
			
			//Marca Ejemplo 1: C�rculo y Texto
			//canvas.drawCircle(centro.x, centro.y, 5, p);
			//canvas.drawText("Santiago", centro.x+10, centro.y+5, p);
			
			//Marca de posici�n inicial
			Bitmap bm = BitmapFactory.decodeResource(
					mapView.getResources(), 
					R.drawable.marcador_google_maps);
			
			canvas.drawBitmap(bm, centro.x - bm.getWidth(), 
					              centro.y - bm.getHeight(), p);
		}
	}
	
	@Override
	public boolean onTap(GeoPoint point, MapView mapView) 
	{
		Context contexto = mapView.getContext();
		String msg = "Lat: " + point.getLatitudeE6()/1E6 + " - " + 
		             "Lon: " + point.getLongitudeE6()/1E6;
		
		Toast toast = Toast.makeText(contexto, msg, Toast.LENGTH_SHORT);
		toast.show();
		
		return true;
	}
}
